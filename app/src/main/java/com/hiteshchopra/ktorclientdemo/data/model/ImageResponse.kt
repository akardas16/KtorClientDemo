package com.hiteshchopra.ktorclientdemo.data.model

import com.google.gson.annotations.SerializedName

class ImageResponse : ArrayList<ImageResponseItem>()

data class ImageResponseItem(
  @SerializedName("blur_hash")
  val blurHash: String?,
  val color: String?,
  @SerializedName("created_at")
  val createdAt: String?,
  @SerializedName("current_user_collections")
  val currentUserCollections: Any?,
  val description: String?,
  val height: Int?,
  val id: String?,
  @SerializedName("liked_by_user")
  val likedByUser: Boolean?,
  val likes: Int?,
  val links: Any?,
  @SerializedName("updated_at")
  val updatedAt: String?,
  val urls: Urls?,
  val user: Any?,
  val width: Int?
)

data class Urls(
  val full: String?,
  val raw: String?,
  val regular: String?,
  val small: String?,
  val thumb: String?
)