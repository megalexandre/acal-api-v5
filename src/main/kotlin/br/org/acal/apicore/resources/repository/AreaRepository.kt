package br.org.acal.apicore.resources.repository

import br.org.acal.apicore.resources.document.AreaDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface AreaRepository : MongoRepository<AreaDocument, String>
