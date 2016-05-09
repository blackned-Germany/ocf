/*
 *  ---------
 * |.##> <##.|  Open Smart Card Development Platform (www.openscdp.org)
 * |#       #|  
 * |#       #|  Copyright (c) 1999-2011 CardContact Software & System Consulting
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
package de.cardcontact.opencard.utils;

import java.io.ByteArrayOutputStream;

public final class Util {
	public static long extractLongFromByteArray(byte[] buffer, int offset, int length) {
		if((offset + length) > buffer.length) {
			throw new IndexOutOfBoundsException("Length exceeds buffer size");
		}
		if(length > 8) {
			throw new IllegalArgumentException("Cannot decode more than 8 byte");
		}
		
		long c = 0;
		while (length-- > 0) {
			c <<= 8;
			c |= buffer[offset + length] & 0xFF;
		}
		return c;
	}
}

