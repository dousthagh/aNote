package co.nikavtech.anote.services.repository.user

import co.nikavtech.anote.database.entities.UserModel


class LoginService {
    fun execute(userModel: UserModel): Boolean {
        return userModel.email == "a@a.a"
    }
}