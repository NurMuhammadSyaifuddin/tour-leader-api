package com.nms.tourleader.repository

import com.nms.tourleader.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository : JpaRepository<ApiKey, String>