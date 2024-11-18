package br.com.joelf.picpay.infraestructure.entrypoint.controllers;

import br.com.joelf.picpay.domain.entities.Transfer;
import br.com.joelf.picpay.domain.usecases.MakeTransferUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final MakeTransferUseCase makeTransferUseCase;

    @PostMapping
    public ResponseEntity<Void> transfer(@RequestBody Transfer transfer) {
        makeTransferUseCase.execute(transfer);
        return ResponseEntity.noContent().build();
    }
}
