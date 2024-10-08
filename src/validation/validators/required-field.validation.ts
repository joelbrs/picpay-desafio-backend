import { MissingParamException } from "@/presentation/exceptions";
import { Validation } from "@/presentation/protocols";

export class RequiredFieldValidation implements Validation {
    constructor(private readonly fieldName: string) {}

    validate(input: any): Error | undefined {
        if (!input[this.fieldName]) {
            return new MissingParamException(this.fieldName);
        }
    }
}
