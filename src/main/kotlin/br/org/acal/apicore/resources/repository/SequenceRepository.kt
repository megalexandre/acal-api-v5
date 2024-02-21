package br.org.acal.apicore.resources.repository

import br.org.acal.apicore.resources.document.SequenceDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface SequenceRepository : MongoRepository<SequenceDocument, String>
