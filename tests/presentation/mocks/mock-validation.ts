import { Validation } from "@/presentation/protocols";

export class ValidationSpy implements Validation {
    error: Error | null = null;
    input: any;

    validate(input: any) {
        this.input = input;
        return this.error;
    }
}
