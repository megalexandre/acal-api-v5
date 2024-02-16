package br.org.acal.apicore.resources.repository

import br.org.acal.apicore.resources.document.LinkDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface LinkRepository : MongoRepository<LinkDocument, String>