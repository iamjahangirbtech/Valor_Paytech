package com.valor_paytech.task.repository.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList


data class Posts (

  @SerializedName("userId" ) var userId : Int?    = null,
  @SerializedName("id"     ) var id     : Int?    = null,
  @SerializedName("title"  ) var title  : String? = null,
  @SerializedName("body"   ) var body   : String? = null,
  @SerializedName("image"   ) var image   : String? = null,
  @SerializedName("comments"   ) var comments   : MutableList<Comments> = ArrayList(),
  @SerializedName("created_at"   ) var created_at   : Long? = null

)