package com.valor_paytech.task.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post_table")
data class PostsModel (
  @PrimaryKey(autoGenerate = true) var ids: Int = 0,
  @SerializedName("userId" ) var userId : Int?    = null,
  @SerializedName("id"     ) var id     : Int?    = null,
  @SerializedName("image"     ) var image     : String?    = null,
  @SerializedName("title"  ) var title  : String? = null,
  @SerializedName("body"   ) var body   : String? = null,
  //@SerializedName("comments"   ) var comments   : Comments? = null,
  @SerializedName("created_at"   ) var created_at   : Long? = null,

  )