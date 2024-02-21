use acal;
SET SQL_SAFE_UPDATES = 0;
-- ---------------------------------------------------------------------------------------
drop function if exists format_reference;

DELIMITER //
CREATE FUNCTION format_reference(entrada TEXT) RETURNS TEXT DETERMINISTIC
BEGIN
    DECLARE saida TEXT;

    -- Remove todos os caracteres não numéricos
    SET saida = REPLACE(SUBSTRING(entrada, 1, 7), "-",".");

    RETURN saida;
END //
DELIMITER ;

-- ---------------------------------------------------------------------------------------



drop function if exists ulid;
DELIMITER //
CREATE FUNCTION ulid () RETURNS CHAR(26) DETERMINISTIC
BEGIN
    DECLARE s_hex CHAR(32);
    SET s_hex = LPAD(HEX(CONCAT(UNHEX(CONV(ROUND(UNIX_TIMESTAMP(CURTIME(4))*1000), 10, 16)), RANDOM_BYTES(10))), 32, '0');
    RETURN REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(CONCAT(LPAD(CONV(SUBSTRING(s_hex, 1, 2), 16, 32), 2, '0'), LPAD(CONV(SUBSTRING(s_hex, 3, 15), 16, 32), 12, '0'), LPAD(CONV(SUBSTRING(s_hex, 18, 15), 16, 32), 12, '0')), 'V', 'Z'), 'U', 'Y'), 'T', 'X'), 'S', 'W'), 'R', 'V'), 'Q', 'T'), 'P', 'S'), 'O', 'R'), 'N', 'Q'), 'M', 'P'), 'L', 'N'), 'K', 'M'), 'J', 'K'), 'I', 'J');
END //
DELIMITER ;
 -- ---------------------------------------------------------------------------------------
drop function if exists person_name;
DELIMITER //
CREATE FUNCTION person_name(p_id INT) RETURNS TEXT DETERMINISTIC
BEGIN
    DECLARE nome_completo TEXT;

    SELECT REPLACE(TRIM(CONCAT(TRIM(nome), ' ', TRIM(sobrenome))),"  "," ")
    INTO nome_completo
    FROM pessoa
    WHERE id = p_id;

    RETURN nome_completo;
END //
DELIMITER ;

 -- ---------------------------------------------------------------------------------------

drop function if exists localdate;
DELIMITER //
CREATE FUNCTION localdate(data_param DATETIME) RETURNS DATE DETERMINISTIC
BEGIN
    DECLARE data_formatada DATE;

    SET data_formatada = DATE(data_param);

    RETURN data_formatada;
END //
DELIMITER ;

 -- ---------------------------------------------------------------------------------------
DROP FUNCTION IF EXISTS localdatetime;
DELIMITER //
CREATE FUNCTION localdatetime(data_param DATETIME) RETURNS VARCHAR(19) DETERMINISTIC
BEGIN
    DECLARE data_formatada VARCHAR(19);

    SET data_formatada = DATE_FORMAT(data_param, '%Y-%m-%dT%H:%i:%s');

    RETURN data_formatada;
END //
DELIMITER ;
 -- ---------------------------------------------------------------------------------------
 
drop function if exists normalize;
DELIMITER //
CREATE FUNCTION normalize(entrada TEXT) RETURNS TEXT DETERMINISTIC
BEGIN
    DECLARE saida TEXT;

    -- Remove todos os caracteres não numéricos
    SET saida = REGEXP_REPLACE(entrada, '[^0-9]', '');

    RETURN saida;
END //
DELIMITER ;


 -- ---------------------------------------------------------------------------------------
drop function if exists clean_document;
DELIMITER //
CREATE FUNCTION clean_document(entrada TEXT) RETURNS TEXT DETERMINISTIC
BEGIN
    DECLARE saida TEXT;
	SET saida = REGEXP_REPLACE(entrada, '[^0-9]', '');
    RETURN saida;
END //
DELIMITER ;
 -- ---------------------------------------------------------------------------------------
 
  -- ---------------------------------------------------------------------------------------
drop function if exists only_numbers;
DELIMITER //
CREATE FUNCTION only_numbers(entrada TEXT) RETURNS TEXT DETERMINISTIC
BEGIN
    DECLARE saida TEXT;
	SET saida = REGEXP_REPLACE(entrada, '[^0-9]', '');
    SET saida = REGEXP_REPLACE(entrada, '[0-9]', '');
    RETURN saida;
END //
DELIMITER ;
 -- ---------------------------------------------------------------------------------------
drop function if exists document;
DELIMITER //
CREATE FUNCTION document(p_id int) RETURNS TEXT DETERMINISTIC
BEGIN    
    DECLARE saida TEXT;

    SELECT  
		CASE 
			WHEN clean_document(cpf) != ''
				THEN clean_document(cpf)
				ELSE clean_document(cnpj)
		END
    INTO saida
    FROM pessoa
    WHERE id = p_id;

    RETURN saida;
END //
DELIMITER ;
 -- ---------------------------------------------------------------------------------------
 -- alter table pessoa drop ulid;
alter table pessoa add column ulid text;
update pessoa set ulid = ulid() ;

drop view if exists customer;
CREATE VIEW customer AS
    SELECT
        p.ulid AS id,
        p.id AS legacy_id,
        PERSON_NAME(p.id) AS name,
        LOCALDATE(p.dataNasc) AS birthDay,
        DOCUMENT(p.id) AS document,
        CASE WHEN p.status = 1 THEN TRUE ELSE FALSE END AS 'active'
    FROM
        pessoa p
    WHERE
        DOCUMENT(p.id) != ''
        order by PERSON_NAME(p.id)
        limit 1000000;


 -- ---------------------------------------------------------------------------------------
alter table endereco add column ulid text;
update endereco set ulid = ulid() ;
	
alter table enderecopessoa add column ulid text;
update enderecopessoa set ulid = ulid() ;

drop view if exists address;
CREATE VIEW address AS
select
	ep.idPessoa,
	ep.ulid as id,
    normalize(ep.Numero) as number,
    e.ulid as areaId,
    trim(concat(trim(e.tipo), ' ',trim(e.nome))) as areaName,
    CASE WHEN  ep.inativo = 0 THEN TRUE ELSE FALSE END AS 'active'
from enderecopessoa ep
	left join endereco e on e.id  = ep.idEndereco
    order by e.tipo, e.nome


-- -------------------------------------
alter table categoriasocio add column ulid text;
update categoriasocio set ulid = ulid() ;

drop view if exists category;
CREATE VIEW category AS
select
	cs.ulid as id,
	cs.nome as name,
	CASE
		WHEN g.id = 1 THEN 'FOUNDING'
		WHEN g.id = 2 THEN 'EFFECTIVE'
		ELSE  'TEMPORARY'
	END AS type,

    t.valor as water,
    t.valor_socio as partnership


from categoriasocio cs
inner join grupo g on g.id = cs.group_id
inner join taxa t on t.id = cs.taxasId;
-- ----------------------------------------------

drop view if exists link;
CREATE VIEW link AS
select
	ep.ulid as id,
	ep.ulid as address,
    p.ulid as customer,
    cs.ulid as category

from enderecopessoa ep
	inner join endereco e on ep.idEndereco = e.id
	inner join pessoa p on ep.idPessoa = p.id
    inner join categoriasocio cs on ep.idCategoriaSocio = cs.id


-- ----------------------------------------------
alter table conta add column ulid text;
update conta set ulid = ulid() ;

drop view if exists invoice;
CREATE VIEW invoice AS

select
	c.ulid as id,
    format_reference(c.dataReferente) as reference,
    localdatetime(c.dataGerada) as emission,
	localdate(c.dataVence) as dueDate,
    ep.ulid as linkId,
    c.valorTaxa as water,
    c.valorOutros as category,
    localdatetime(c.dataPag) as dataPaid
from conta c
	inner join enderecopessoa ep on c.idEnderecoPessoa = ep.id
	inner join categoriasocio cs on cs.id = ep.idCategoriaSocio





----------------