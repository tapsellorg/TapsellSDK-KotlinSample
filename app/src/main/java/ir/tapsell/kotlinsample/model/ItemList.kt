package ir.tapsell.kotlinsample.model

import java.io.Serializable

class ItemList : Serializable {
    var listItemType: ListItemType? = null
    var id: String? = null
    var title: String? = null
}
