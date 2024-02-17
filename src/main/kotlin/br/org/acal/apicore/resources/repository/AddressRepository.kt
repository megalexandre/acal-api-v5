package br.org.acal.apicore.resources.repository

import br.org.acal.apicore.resources.document.AddressDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface AddressRepository : MongoRepository<AddressDocument, String>