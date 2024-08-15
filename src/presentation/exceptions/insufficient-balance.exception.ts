export class InsufficientBalanceException extends Error {
    constructor() {
        super("Insufficient Balance");
        this.name = "InsufficientBalanceException";
    }
}
