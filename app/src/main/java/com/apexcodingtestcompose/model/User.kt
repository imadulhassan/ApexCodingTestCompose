package com.apex.codeassesment.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@JsonClass(generateAdapter = true)
data class User(
  var gender: String? = null,
  val name: Name? = null,
  var location: Location? = null,
  var email: String? = null,
  var login: Login? = null,
  var dob: Dob? = null,
  var registered: Dob? = null,
  var phone: String? = null,
  var cell: String? = null,
  var id: Id? = null,
  var picture: Picture? = null,
  var nat: String? = null
) : Parcelable {

  // TODO (2 point): Add tests
  companion object {
    fun createRandom(): User {
      return User(
        name = Name(first = randomName(), last = randomName()),
        location = Location(coordinates = Coordinates(randomDouble().toString(), randomDouble().toString())),
        email = randomEmail() + "@gmail.com",
        dob = Dob(age = 25)
      )
    }
    private fun randomName(): String {
      val names = listOf("John", "Jane", "Michael", "Emily", "David", "Emma", "Sarah", "William")
      return names.random()
    }


     fun randomImage(): String {
      val names = listOf("https://randomuser.me/api/portraits/med/men/22.jpg", "https://randomuser.me/api/portraits/med/men/80.jpg", "https://randomuser.me/api/portraits/women/1.jpg", )
      return names.random()
    }

    private fun randomEmail(): String {
      val domains = listOf("gmail.com", "yahoo.com", "hotmail.com", "outlook.com")
      return "${randomString()}@${domains.random()}"
    }

    private fun randomString() = randomName()
    private fun randomDouble() = Random().nextDouble() * 100
  }
}
