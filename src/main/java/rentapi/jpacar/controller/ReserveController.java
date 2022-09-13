package rentapi.jpacar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentapi.jpacar.domain.Reservation;
import rentapi.jpacar.domain.ReservationDto;
import rentapi.jpacar.service.ReserveService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReserveController {

    private final ReserveService reserveService;


    @PostMapping("/reserve/regist")
    public ResponseEntity<Long> regist(@RequestBody ObjectNode objectNode) throws JsonProcessingException {
        long result = reserveService.regist(objectNode);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/reserve/{no}")
    public ResponseEntity<List<Reservation>> findReserve(@PathVariable("no") long reserveId) {
        return ResponseEntity.ok(reserveService.findReserve(reserveId));
    }

    @GetMapping("/reserve/dto/{no}")
    public ResponseEntity<List<ReservationDto>> findReserveDto(@PathVariable("no") long reserveId) {
        return ResponseEntity.ok(reserveService.findReserveDto(reserveId));
    }

    @GetMapping("/reserves")
    public ResponseEntity<List<Reservation>> listReserve() {
        return ResponseEntity.ok(reserveService.findAllReserve());
    }

    @GetMapping("/reserves/dto")
    public ResponseEntity<List<ReservationDto>> listReserveDto() {
        return ResponseEntity.ok(reserveService.findAllReserveDto());
    }
}
