package news

import kotlinx.rpc.annotations.Rpc

@Rpc
interface NewsClient {
    suspend fun request(station: Station): List<NewsResponse>
}