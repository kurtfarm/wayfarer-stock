package com.dkprint.app.copperplate.domain.entity

import com.dkprint.app.copperplate.domain.entity.value.GlossType
import com.dkprint.app.core.AggregateRoot
import com.dkprint.app.core.infrastructure.jpa.shared.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.OneToMany
import jakarta.persistence.FetchType
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

@AggregateRoot
@Entity
@SQLDelete(sql = "UPDATE copperplate SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "copperplate")
class Copperplate(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0L,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "code_id", nullable = false)
    var codeId: Long,

    @Column(name = "vendor_id", nullable = false)
    var vendorId: Long,

    @Column(name = "fabric_width")
    val fabricWidth: Int,

    @Column(name = "location", nullable = false)
    val location: String,

    @Column(name = "circumference", nullable = false)
    val circumference: Int,

    @Column(name = "length", nullable = false)
    val length: Int,

    @Column(name = "product_Image")
    val productImage: String?,

    @Column(name = "gloss_type", nullable = false)
    @Enumerated(EnumType.STRING)
    val glossType: GlossType,

    @OneToMany(mappedBy = "copperPlate", fetch = FetchType.LAZY)
    val histories: List<CopperplateHistory> = mutableListOf(),

    @Column(name = "comment")
    var comment: String?,
) : BaseEntity()
