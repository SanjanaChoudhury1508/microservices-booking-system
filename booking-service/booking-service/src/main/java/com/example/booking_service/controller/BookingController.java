package com.example.booking_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class BookingController {

    @GetMapping("/bookings")
    public String getBookings() {
        return "Booking service accessed successfully!";
    }

    @GetMapping("/bookings/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminBookings() {
        return "Admin booking data";
    }
}
