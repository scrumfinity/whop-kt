package dev.lepshee.whop.models.shipments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Request body for `POST /shipments`. */
@Serializable
data class CreateShipmentRequest(
    /** Company that owns the payment being shipped. */
    @SerialName("company_id") val companyId: String,
    /** Payment to associate with the shipment. */
    @SerialName("payment_id") val paymentId: String,
    /** Carrier tracking code for the shipment. */
    @SerialName("tracking_code") val trackingCode: String,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(paymentId.isNotBlank()) { "Payment ID must not be blank." }
        require(trackingCode.isNotBlank()) { "Tracking code must not be blank." }
    }
}

/** Shipment item returned by list endpoints. */
@Serializable
data class ShipmentListItem(
    /** Unique shipment ID. */
    val id: String,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String,
    /** Current shipment delivery status. */
    val status: ShipmentStatus,
    /** More granular shipment status, when supplied by the carrier. */
    val substatus: ShipmentSubstatus? = null,
    /** Carrier-assigned tracking code. */
    @SerialName("tracking_code") val trackingCode: String,
    /** Last update timestamp. */
    @SerialName("updated_at") val updatedAt: String,
    /** Carrier identifier returned by Whop. */
    val carrier: String,
    /** Carrier service level, when available. */
    val service: String? = null,
    /** Estimated delivery timestamp, when available. */
    @SerialName("delivery_estimate") val deliveryEstimate: String? = null,
    /** Payment associated with the shipment, when accessible. */
    val payment: ShipmentPayment? = null,
)

/** Shipment returned by create and retrieve endpoints. */
@Serializable
data class Shipment(
    /** Unique shipment ID. */
    val id: String,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String,
    /** Current shipment delivery status. */
    val status: ShipmentStatus,
    /** More granular shipment status, when supplied by the carrier. */
    val substatus: ShipmentSubstatus? = null,
    /** Carrier-assigned tracking code. */
    @SerialName("tracking_code") val trackingCode: String,
    /** Last update timestamp. */
    @SerialName("updated_at") val updatedAt: String,
    /** Carrier identifier returned by Whop. */
    val carrier: String,
    /** Carrier service level, when available. */
    val service: String? = null,
    /** Estimated delivery timestamp, when available. */
    @SerialName("delivery_estimate") val deliveryEstimate: String? = null,
    /** Payment associated with the shipment, when accessible. */
    val payment: ShipmentPayment? = null,
)

/** Minimal payment reference nested in shipment responses. */
@Serializable
data class ShipmentPayment(
    /** Unique payment ID. */
    val id: String,
)

/** Shipment status values documented by Whop. */
@Serializable
enum class ShipmentStatus {
    @SerialName("unknown")
    Unknown,

    @SerialName("pre_transit")
    PreTransit,

    @SerialName("in_transit")
    InTransit,

    @SerialName("out_for_delivery")
    OutForDelivery,

    @SerialName("delivered")
    Delivered,

    @SerialName("available_for_pickup")
    AvailableForPickup,

    @SerialName("return_to_sender")
    ReturnToSender,

    @SerialName("failure")
    Failure,

    @SerialName("cancelled")
    Cancelled,

    @SerialName("error")
    Error,
}

/** Shipment substatus values documented by Whop. */
@Serializable
enum class ShipmentSubstatus {
    @SerialName("address_correction")
    AddressCorrection,

    @SerialName("arrived_at_destination")
    ArrivedAtDestination,

    @SerialName("arrived_at_facility")
    ArrivedAtFacility,

    @SerialName("arrived_at_pickup_location")
    ArrivedAtPickupLocation,

    @SerialName("awaiting_information")
    AwaitingInformation,

    @SerialName("substatus_cancelled")
    SubstatusCancelled,

    @SerialName("damaged")
    Damaged,

    @SerialName("delayed")
    Delayed,

    @SerialName("delivery_exception")
    DeliveryException,

    @SerialName("departed_facility")
    DepartedFacility,

    @SerialName("departed_origin_facility")
    DepartedOriginFacility,

    @SerialName("expired")
    Expired,

    @SerialName("substatus_failure")
    SubstatusFailure,

    @SerialName("held")
    Held,

    @SerialName("substatus_in_transit")
    SubstatusInTransit,

    @SerialName("label_created")
    LabelCreated,

    @SerialName("lost")
    Lost,

    @SerialName("missorted")
    Missorted,

    @SerialName("substatus_out_for_delivery")
    SubstatusOutForDelivery,

    @SerialName("received_at_destination_facility")
    ReceivedAtDestinationFacility,

    @SerialName("received_at_origin_facility")
    ReceivedAtOriginFacility,

    @SerialName("refused")
    Refused,

    @SerialName("return")
    Return,

    @SerialName("status_update")
    StatusUpdate,

    @SerialName("transferred_to_destination_carrier")
    TransferredToDestinationCarrier,

    @SerialName("transit_exception")
    TransitException,

    @SerialName("substatus_unknown")
    SubstatusUnknown,

    @SerialName("weather_delay")
    WeatherDelay,
}
