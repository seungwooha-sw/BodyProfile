package study.android.bodyprofile.api.model

data class TransferPapagoResponse(
    val transferBody: TransferBody
)

data class TransferBody(
    val transferData: TransferData
)

data class TransferData(
    val srcLangType: String = "",
    val tarLangType: String = "",
    val translatedText: String = ""
)