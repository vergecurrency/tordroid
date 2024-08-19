package com.vergepay.core.wallet.families.clams;

import static com.vergepay.core.Preconditions.checkArgument;

import com.google.common.base.Charsets;
import com.vergepay.core.messages.MessageFactory;
import com.vergepay.core.messages.TxMessage;
import com.vergepay.core.wallet.AbstractTransaction;
import com.vergepay.core.wallet.families.bitcoin.BitTransaction;

import org.bitcoinj.core.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

/**
 * @author John L. Jegutanis
 */
public class ClamsTxMessage implements TxMessage {
    public static final int MAX_MESSAGE_BYTES = 140;
    private static final Logger log = LoggerFactory.getLogger(ClamsTxMessage.class);
    private final static ClamsMessageFactory instance = new ClamsMessageFactory();
    private String message;

    ClamsTxMessage(String message) {
        setMessage(message);
    }

    public static MessageFactory getFactory() {
        return instance;
    }

    public static ClamsTxMessage create(String message) throws IllegalArgumentException {
        return new ClamsTxMessage(message);
    }

    @Nullable
    public static ClamsTxMessage parse(AbstractTransaction tx) {
        try {
            Transaction rawTx = ((BitTransaction) tx).getRawTransaction();
            byte[] bytes = rawTx.getExtraBytes();
            if (bytes == null || bytes.length == 0) return null;
            checkArgument(bytes.length <= MAX_MESSAGE_BYTES, "Maximum data size exceeded");

            return new ClamsTxMessage(new String(bytes, Charsets.UTF_8));
        } catch (Exception e) {
            log.info("Could not parse message: {}", e.getMessage());
            return null;
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    static byte[] serialize(String message) {
        return message.getBytes(Charsets.UTF_8);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        checkArgument(serialize(message).length <= MAX_MESSAGE_BYTES, "Message is too big");
        this.message = message;
    }

    public boolean isEmpty() {
        return isNullOrEmpty(message);
    }

    @Override
    public Type getType() {
        return Type.PUBLIC; // Only public is supported
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public void serializeTo(AbstractTransaction transaction) {
        if (transaction instanceof BitTransaction) {
            Transaction rawTx = ((BitTransaction) transaction).getRawTransaction();
            rawTx.setExtraBytes(serialize(message));
        }
    }

    public static class ClamsMessageFactory implements MessageFactory {
        @Override
        public int maxMessageSizeBytes() {
            return MAX_MESSAGE_BYTES;
        }

        @Override
        public boolean canHandlePublicMessages() {
            return true;
        }

        @Override
        public boolean canHandlePrivateMessages() {
            return false;
        }

        @Override
        public TxMessage createPublicMessage(String message) {
            return create(message);
        }

        @Nullable
        @Override
        public TxMessage extractPublicMessage(AbstractTransaction transaction) {
            return parse(transaction);
        }
    }
}