package br.com.picpay.application.presentation;

import br.com.picpay.application.presentation.dtos.transfer.CreateTransferDto;
import br.com.picpay.domain.Transfer;
import br.com.picpay.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transfer transfer(@RequestBody @Valid CreateTransferDto transfer) {
        return transferService.create(transfer.toDomain());
    }
}
