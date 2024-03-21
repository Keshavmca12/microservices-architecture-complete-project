package com.marktek.config;

import com.nimbusds.oauth2.sdk.id.Issuer;

public interface IssuerProvider  {

   Issuer getIssuer();
}
