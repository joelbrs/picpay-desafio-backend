import { Validation } from "@/presentation/protocols";
import { RequiredFieldValidation, ValidationComposite } from "@/validation";

export const makeCreateTransactionValidation = (): Validation => {
    const validations: Validation[] = [];

    for (const field of ["payeeId", "payerId", "value"]) {
        validations.push(new RequiredFieldValidation(field));
    }
    return new ValidationComposite(validations);
};
