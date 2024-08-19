package com.vergepay.core.coins.nxt;

import java.util.List;

public interface Transaction extends Comparable<Transaction> {


    long getId();

    String getStringId();

    long getSenderId();

    byte[] getSenderPublicKey();

    long getRecipientId();

    int getHeight();

    void setHeight(int height);

    int getTimestamp();

    int getConfirmations();

    void setConfirmations(int confirmations);

    int getBlockTimestamp();

    short getDeadline();

    int getExpiration();

    long getAmountNQT();

    long getFeeNQT();

    String getReferencedTransactionFullHash();

    byte[] getSignature();

    String getFullHash();

    TransactionType getType();

    Attachment getAttachment();

    void sign(String secretPhrase);

    void sign(byte[] privateKey);

    byte[] getBytes();

    byte[] getUnsignedBytes();

    byte getVersion();

    //JSONObject getJSONObject();

    Appendix.Message getMessage();

    Appendix.EncryptedMessage getEncryptedMessage();

    Appendix.EncryptToSelfMessage getEncryptToSelfMessage();

    List<? extends Appendix> getAppendages();

    int getECBlockHeight();

    /*
    Collection<TransactionType> getPhasingTransactionTypes();

    Collection<TransactionType> getPhasedTransactionTypes();
    */

    long getECBlockId();

    interface Builder {

        Builder recipientId(long recipientId);

        Builder referencedTransactionFullHash(String referencedTransactionFullHash);

        Builder message(Appendix.Message message);

        Builder encryptedMessage(Appendix.EncryptedMessage encryptedMessage);

        Builder encryptToSelfMessage(Appendix.EncryptToSelfMessage encryptToSelfMessage);

        Builder publicKeyAnnouncement(Appendix.PublicKeyAnnouncement publicKeyAnnouncement);

        Transaction build() throws NxtException.NotValidException;

    }

}
