package co.nikavtech.anote.models

data class UserModel(
    private var id: Int? = null,
    private var email: String? = null,
    private var password: String? = null
) {
    var _id: Int? = id
        get() = field
        set(value) {
            field = value
        }

    var _email: String? = email
        get() = field
        set(value) {
            field = value
        }

    var _password: String? = password
        get() = field
        set(value) {
            field = value
        }

}
