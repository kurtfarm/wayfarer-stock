package com.dkprint.app.copperplate.domain.repository

import com.dkprint.app.copperplate.domain.entity.Copperplate
import org.springframework.data.jpa.repository.JpaRepository

interface CopperplateRepository : JpaRepository<Copperplate, Long>
