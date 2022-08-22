package com.owow.characterapp.domain.entities

data class CharacterResponse(
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val image: String
)