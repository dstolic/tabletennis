package com.ds.microservices.sport.tabletennis.util;

import org.hashids.Hashids;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;

public final class IdentifierUtil {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int MIN_HASH_LENGTH = 9;

    private IdentifierUtil() {
    }

    public static String encode(Long number, String salt) {
        if (number == null) {
            return null;
        }

        Hashids hashids = new Hashids(salt, MIN_HASH_LENGTH, ALPHABET);
        String hash = hashids.encode(number);
        return new StringBuilder(hash).insert(3, "-").insert(7, "-").toString();
    }

    public static Long decode(String hash, String salt) {
        if (StringUtils.isEmpty(hash)) {
            return null;
        }

        Hashids hashids = new Hashids(salt, MIN_HASH_LENGTH, ALPHABET);
        hash = hash.replace("-", "");
        long[] decode = hashids.decode(hash);

        if (decode.length == 0) {
            throw new ValidationException("Id is not valid.");
        }

        return decode[0];
    }
}