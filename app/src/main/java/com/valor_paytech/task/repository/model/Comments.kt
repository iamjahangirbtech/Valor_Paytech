package com.valor_paytech.task.repository.model

import com.google.gson.annotations.SerializedName


data class Comments (


  @SerializedName("postId" ) var postId : Int?    = null,
  @SerializedName("id"     ) var id     : Int?    = null,
  @SerializedName("name"   ) var name   : String? = null,
  @SerializedName("email"  ) var email  : String? = null,
  @SerializedName("body"   ) var body   : String? = null

)