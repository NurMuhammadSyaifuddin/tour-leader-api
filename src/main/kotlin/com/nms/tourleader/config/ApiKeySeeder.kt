package com.nms.tourleader.config

import com.nms.tourleader.entity.ApiKey
import com.nms.tourleader.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ApiKeySeeder(val repository: ApiKeyRepository) : ApplicationRunner{

    val apiKey = "secret_tour_leader_api"

    override fun run(args: ApplicationArguments?) {
        if (!repository.existsById(apiKey)){
            val entity = ApiKey(apiKey)
            repository.save(entity)
        }
    }
}