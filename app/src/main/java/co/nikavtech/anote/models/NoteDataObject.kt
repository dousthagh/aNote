package co.nikavtech.anote.models

data class NoteDataObject(
    private var _id: Int? = null,
    private var _title: String? = null,
    private var _text: String? = null,
    private var _insertedDate: String? = "2021-04-10",
    private var _categoryId: Int? = null
) {
    var id: Int? = _id
        get() = field
        set(value) {
            field = value
        }
    var title: String? = _title
        get() = field
        set(value) {
            field = value
        }
    var text: String? = _text
        get() = field
        set(value) {
            field = value
        }
    var insertedDate: String? = _insertedDate
        get() = field
        set(value) {
            field = value
        }
    var categoryId: Int? = _categoryId
        get() = field
        set(value) {
            field = value
        }

}