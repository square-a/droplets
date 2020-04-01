package eu.asquare.droplets.presentation

data class DropletViewModel(
    val unread: List<DropletResource>,
    val read: List<DropletResource>
)