/*
 *  ---------
 * |.##> <##.|  Open Smart Card Development Platform (www.openscdp.org)
 * |#       #|  
 * |#       #|  Copyright (c) 1999-2012 CardContact Software & System Consulting
 * |'##> <##'|  Andreas Schwier, 32429 Minden, Germany (www.cardcontact.de)
 *  --------- 
 *
 *  This file is part of OpenSCDP.
 *
 *  OpenSCDP is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License version 2 as
 *  published by the Free Software Foundation.
 *
 *  OpenSCDP is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSCDP; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package de.cardcontact.opencard.service.smartcardhsm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import de.cardcontact.tlv.ConstructedTLV;
import de.cardcontact.tlv.PrimitiveTLV;
import de.cardcontact.tlv.TLVEncodingException;
import de.cardcontact.tlv.Tag;

public class CertificateDescription {
	
	public static byte[] buildCertDescription(String label, PublicKey subjectPublicKey, byte[] certEF) throws TLVEncodingException {
		return buildCertDescription(label, null, subjectPublicKey, certEF);
	}
	
	private static byte[] computeSubjectKeyID(PublicKey key) {
		byte[] sha1 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			sha1 = md.digest(key.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// ignore
		}
		return sha1;
	}
	
	public static byte[] buildCertDescription(String label, byte[] commonObjectFlags, PublicKey subjectPublicKey, byte[] certEF) throws TLVEncodingException {
		ConstructedTLV tlv = new ConstructedTLV(0x30);
		ConstructedTLV commonObjectAttributes = new ConstructedTLV(0x30);
		commonObjectAttributes.add(new PrimitiveTLV(Tag.UTF8String, label.getBytes()));
		
		if (commonObjectFlags == null) {
			commonObjectFlags = new byte[] {0x06, 0x40};
		}
		commonObjectAttributes.add(new PrimitiveTLV(Tag.BIT_STRING, commonObjectFlags));
		
		ConstructedTLV commonCertificateAttributes = new ConstructedTLV(0x30);
		commonCertificateAttributes.add(new PrimitiveTLV(Tag.OCTET_STRING, computeSubjectKeyID(subjectPublicKey)));
		
		ConstructedTLV typeAttributes = new ConstructedTLV(0xA1);		
		ConstructedTLV x509CertificateAttributes = new ConstructedTLV(0x30);
		ConstructedTLV path = new ConstructedTLV(0x30);
		path.add(new PrimitiveTLV(Tag.OCTET_STRING, certEF));
		x509CertificateAttributes.add(path);
		typeAttributes.add(x509CertificateAttributes);
		
		tlv.add(commonObjectAttributes);
		tlv.add(commonCertificateAttributes);
		tlv.add(typeAttributes);
		
		return tlv.getBytes();
	}
	
	
	
	public byte[] buildCertDescription(String label) throws TLVEncodingException {
		ConstructedTLV tlv = new ConstructedTLV(0x30);
		ConstructedTLV labelSequence = new ConstructedTLV(0x30);
		labelSequence.add(new PrimitiveTLV(Tag.UTF8String, label.getBytes()));
		tlv.add(labelSequence);
		return tlv.getBytes();
	}
	
	
	
	public String getLabel(byte[] enc) throws TLVEncodingException {
		ConstructedTLV tlv = new ConstructedTLV(enc);
		tlv = (ConstructedTLV) tlv.get(0);
		if (tlv.getElements() < 1) {
			throw new TLVEncodingException("The description is wrong encoded");
		}
		String label = new String(tlv.get(0).getValue());
		return label;
	}
}
