package br.org.acal.apicore.infrastructure.kotlin.extensions

inline fun Boolean.alsoTrue(block: () ->Unit): Boolean {
    if(this){
        block()
    }
    return this
}

inline fun Boolean.alsoFalse(block: () ->Unit): Boolean {
    if(!this){
        block()
    }
    return this
}