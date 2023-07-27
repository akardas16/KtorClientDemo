package com.hiteshchopra.ktorclientdemo.data.model

import com.google.gson.annotations.SerializedName

data class UserListResponse (
    val page: Long,

    @SerializedName("per_page")
    val perPage: Long,
    val total: Long,
    @SerializedName("total_pages")
    val totalPages: Long,
    val data: List<Datum>,
    val support: Support
)

data class Datum (
    val id: Long,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    val avatar: String
)

data class Support (
    val url: String,
    val text: String
)
