package com.fabiang.flightcheckin.integration;

import com.fabiang.flightcheckin.integration.dto.Reservation;
import com.fabiang.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {
	
	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(ReservationUpdateRequest request);

}
