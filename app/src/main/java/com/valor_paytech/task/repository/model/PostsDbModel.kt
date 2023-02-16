package com.valor_paytech.task.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post_db_table")
data class PostsDbModel (
  @PrimaryKey(autoGenerate = true) var ids: Int = 0,
  @SerializedName("userId" ) var userId : Int?    = null,
  @SerializedName("id"     ) var id     : Int?    = null,
  @SerializedName("image"     ) var image     : String?    = null,
  @SerializedName("created_at"   ) var created_at   : Long? = null,
  )