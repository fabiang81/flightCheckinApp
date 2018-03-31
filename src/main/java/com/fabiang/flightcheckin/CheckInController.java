package com.fabiang.flightcheckin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fabiang.flightcheckin.integration.ReservationRestClient;
import com.fabiang.flightcheckin.integration.dto.Reservation;
import com.fabiang.flightcheckin.integration.dto.ReservationUpdateRequest;

@Controller
public class CheckInController {
	
	@Autowired
	ReservationRestClient restClient;
	
	@RequestMapping("/")
	public String checkInHome() {
		return "index";
	}
	
	@RequestMapping("/showStartCheckin")
	public String showStartCheckIn(@ModelAttribute("reservation") Reservation reservation) {
		return "startCheckIn";
	}
	
	@RequestMapping("/startCheckIn")
	public String startCheckIn(@RequestParam("id") Long reservationId, ModelMap modelMap, 
			@ModelAttribute("reservationRequest") ReservationUpdateRequest reservationRequest) {
		Reservation reservation = restClient.findReservation(reservationId);
		modelMap.addAttribute("reservation", reservation);
		return "displayReservationDetails";
	}
	
	@RequestMapping("/completeCheckIn")
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId, 
			@RequestParam("numberOfBags") int numberOfBags, ModelMap modelMap) {
		modelMap.addAttribute("msg", "Check In confirmed succesfully");
		ReservationUpdateRequest request = new ReservationUpdateRequest();
		request.setId(reservationId);
		request.setCheckedIn(true);
		request.setNumberOfBags(numberOfBags);
		restClient.updateReservation(request);
		return "checkInConfirmation";
		
	}

}
