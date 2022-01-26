package com.example.baeminclone.util

private fun getRandomImageUrls() : List<String> = listOf(
    "https://images.unsplash.com/photo-1641353989082-9b15fa661805?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=200&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY0MzA3NzcxMg&ixlib=rb-1.2.1&q=80&w=1200",
    "https://images.unsplash.com/photo-1640739689052-50e0a2bfd9a3?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=200&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY0MzA3OTk3MA&ixlib=rb-1.2.1&q=80&w=1200",
    "https://images.unsplash.com/photo-1640551497504-ec05b9e50b50?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=200&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY0MzA3OTk4MQ&ixlib=rb-1.2.1&q=80&w=1200",
    "https://images.unsplash.com/photo-1640527319862-21bad4f86cda?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=200&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY0MzA3OTk5MA&ixlib=rb-1.2.1&q=80&w=1200",
    "https://images.unsplash.com/photo-1642326828805-0d3251cc250f?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=200&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY0MzA4MDAwMg&ixlib=rb-1.2.1&q=80&w=1200",
    "https://i.picsum.photos/id/866/1200/300.jpg?hmac=w0HrPetIJ-TtyvBWTo9-FoAIMUywm-PjQUarlaGv9oQ",
    "https://i.picsum.photos/id/91/1200/600.jpg?hmac=tP85HaKaSHEsHqXDjkoAJwVM1S49D2FzWMu_P_oxTw0",
    "https://i.picsum.photos/id/572/1200/600.jpg?hmac=aGa6D2sjOvhz-Xmy1f8VbNZxs8zQ6ez8nsV-JJipW7A",
    "https://i.picsum.photos/id/289/1200/600.jpg?hmac=AGMKWRbH0HhFJbqUFGDhLrodE9o92QEjhwQQPW10K24",
    "https://i.picsum.photos/id/379/1200/600.jpg?hmac=4LYRwjjC71LoiMlx8K__mPwdsEehq1fBtiJ_hwM4PEM",
    "https://i.picsum.photos/id/824/1200/600.jpg?hmac=BjcRCHrsmwpOtqDJF39bnZ5N_MXQdLlTQk0wn64zARU",
    "https://i.picsum.photos/id/379/1200/600.jpg?hmac=4LYRwjjC71LoiMlx8K__mPwdsEehq1fBtiJ_hwM4PEM",
    "https://i.picsum.photos/id/289/1200/600.jpg?hmac=AGMKWRbH0HhFJbqUFGDhLrodE9o92QEjhwQQPW10K24",
    "https://i.picsum.photos/id/572/1200/600.jpg?hmac=aGa6D2sjOvhz-Xmy1f8VbNZxs8zQ6ez8nsV-JJipW7A",
    "https://picsum.photos/1200/600/?random"
)

fun getRandomImageList(size: Int): List<String> = getRandomImageUrls().shuffled().subList(0, size)

fun getRandomImage() : String = getRandomImageUrls().shuffled()[0]
