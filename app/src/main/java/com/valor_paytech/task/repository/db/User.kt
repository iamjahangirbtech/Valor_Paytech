package com.valor_paytech.task.repository.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) var ids: Int = 0,
    @SerializedName("id") var id: Int = 0,
    @SerializedName("email") var email: String? = null,
    @SerializedName("first_name") var first_name: String? = null,
    @SerializedName("last_name") var last_name: String? = null,
    @SerializedName("avatar") var avatar: String? = null,
    )
