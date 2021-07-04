package com.endorocket.hexagonalapp.domain.apartmentbookinghistory;

import com.endorocket.hexagonalapp.domain.period.Period;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
public class ApartmentBooking {
	@Enumerated(EnumType.STRING)
	private BookingStep bookingStep;
	private LocalDateTime bookingDateTime;
	private String ownerId;
	private String tenantId;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "start", column = @Column(name = "period_start")),
			@AttributeOverride(name = "end", column = @Column(name = "period_end"))
	})
	private Period period;

	private ApartmentBooking() {
	}

	private ApartmentBooking(BookingStep bookingStep, LocalDateTime bookingDateTime, String ownerId, String tenantId, Period period) {
		this.bookingStep = bookingStep;
		this.bookingDateTime = bookingDateTime;
		this.ownerId = ownerId;
		this.tenantId = tenantId;
		this.period = period;
	}

	public static ApartmentBooking start(LocalDateTime bookingDateTime, String ownerId, String tenantId, Period period) {
		return new ApartmentBooking(BookingStep.START, bookingDateTime, ownerId, tenantId, period);
	}
}
