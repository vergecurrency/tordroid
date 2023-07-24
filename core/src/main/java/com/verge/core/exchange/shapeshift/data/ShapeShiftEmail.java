package com.vergepay.core.exchange.shapeshift.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author John L. Jegutanis
 */
public class ShapeShiftEmail  extends ShapeShiftBase {
    public final Status status;
    public final String message;

    public enum Status {
        SUCCESS, UNKNOWN
    }

    public ShapeShiftEmail(JSONObject data) throws ShapeShiftException {
        super(data);
        if (!isError) {
            try {
                JSONObject innerData = data.getJSONObject("email");
                message = innerData.getString("message");
                String statusStr = innerData.getString("status");
                if ("success".equals(statusStr)) {
                    status = Status.SUCCESS;
                } else {
                    status = Status.UNKNOWN;
                }
            } catch (JSONException e) {
                throw new ShapeShiftException("Could not parse object", e);
            }
        } else {
            status = null;
            message = null;
        }
    }
}