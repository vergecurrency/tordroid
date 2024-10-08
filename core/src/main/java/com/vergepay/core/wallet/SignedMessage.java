package com.vergepay.core.wallet;

import static com.vergepay.core.Preconditions.checkNotNull;

import javax.annotation.Nullable;

/**
 * @author John L. Jegutanis
 */
final public class SignedMessage {
    final String message;
    final String address;
    String signature;
    Status status = Status.Unknown;
    public SignedMessage(String address, String message, String signature) {
        this.address = checkNotNull(address);
        this.message = checkNotNull(message);
        this.signature = signature;
    }

    public SignedMessage(String address, String message) {
        this(address, message, null);
    }

    public SignedMessage(SignedMessage otherMessage, Status newStatus) {
        message = otherMessage.message;
        address = otherMessage.address;
        signature = otherMessage.signature;
        status = newStatus;
    }

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }

    @Nullable
    public String getSignature() {
        return signature;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        SignedOK, VerifiedOK, Unknown, AddressMalformed, KeyIsEncrypted, MissingPrivateKey,
        InvalidSigningAddress, InvalidMessageSignature
    }
}
