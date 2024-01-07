package com.example.tvor_project.data_classes

data class Schedule(
    val table: Table,
    val weeks: List<Int>
)

data class Table(
    val type: String,
    val name: String,
    val week: Int,
    val group: String,
    val table: List<List<String>>,
    val link: String
)