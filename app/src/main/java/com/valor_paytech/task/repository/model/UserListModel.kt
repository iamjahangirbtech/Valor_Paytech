package com.valor_paytech.task.repository.model

import com.google.gson.annotations.SerializedName


data class UserListModel (

  @SerializedName("page"        ) var page       : Int?            = null,
  @SerializedName("per_page"    ) var perPage    : Int?            = null,
  @SerializedName("total"       ) var total      : Int?            = null,
  @SerializedName("total_pages" ) var totalPages : Int?            = null,
  @SerializedName("data"        ) var data       : List<Data> = arrayListOf(),
  @SerializedName("support"     ) var support    : Support?        = Support()

)