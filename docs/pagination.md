# Pagination

Whop list endpoints use cursor pagination.

```kotlin
val page = whop.products.list(
    ListProductsRequest(companyId = "biz_...", first = 25),
)

println(page.pageInfo.endCursor)
```

For coroutine-friendly iteration, use auto-paging:

```kotlin
whop.plans.listAutoPaging(
    ListPlansRequest(companyId = "biz_...", first = 25),
).collect { plan ->
    println(plan.id)
}
```

Auto-paging currently follows forward cursors (`after` / `end_cursor`).
