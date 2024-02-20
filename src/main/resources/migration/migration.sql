use acal;
SET SQL_SAFE_UPDATES = 0;
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

    SELECT TRIM(CONCAT(TRIM(nome), ' ', TRIM(sobrenome)))
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
    person_name(p.id) AS name,
    localdate(p.dataNasc) AS birthday,
    document(p.id) AS document
FROM pessoa p
WHERE document(p.id) != '';

 -- ---------------------------------------------------------------------------------------
alter table endereco add column ulid text;
update endereco set ulid = ulid() ;
	
alter table enderecopessoa add column ulid text;
update enderecopessoa set ulid = ulid() ;

drop view if exists address;
CREATE VIEW address AS
select 
	ep.ulid as id,
    e.ulid as areaId,
	trim(trim(e.tipo)), ' ',concat(trim(e.nome)) as areaName,
    normalize(ep.Numero) as number
from enderecopessoa ep 
	inner join endereco e on e.id  = ep.id
    where ep.inativo = 0
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
		WHEN g.id = 1 THEN 'Sócio Fundador'
		WHEN g.id = 2 THEN 'Sócio Efetivo'
		ELSE  'Temporário'
	END AS type,

    t.valor as water,
    t.valor_socio as partnership


from categoriasocio cs
inner join grupo g on g.id = cs.group_id
inner join taxa t on t.id = cs.taxasId;
