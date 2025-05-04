package com.dkprint.app.copperplate.domain.entity

import com.dkprint.app.copperplate.domain.entity.value.GlossType
import com.dkprint.app.copperplate.domain.entity.value.PlatePosition
import com.dkprint.app.core.AggregateRoot
import com.dkprint.app.core.infrastructure.jpa.shared.BaseEntity
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
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
    var fabricWidth: Int,

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "plate_position",
        joinColumns = [JoinColumn(name = "copperplate_id")]
    )
    var platePositions: List<PlatePosition> = mutableListOf(),

    @Column(name = "plate_position_comment")
    var platePositionComment: String? = null,

    @Column(name = "location", nullable = false)
    var location: String,

    @Column(name = "circumference", nullable = false)
    var circumference: Int,

    @Column(name = "length", nullable = false)
    var length: Int,

    @Column(name = "product_Image")
    var productImage: String? = null,

    @Column(name = "gloss_type", nullable = false)
    @Enumerated(EnumType.STRING)
    var glossType: GlossType,

    @OneToMany(mappedBy = "copperPlate", fetch = FetchType.LAZY)
    var histories: List<CopperplateHistory> = mutableListOf(),

    @Column(name = "comment", length = 100)
    var comment: String? = null,
) : BaseEntity() {
}
