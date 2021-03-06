#
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
#
# Copyright (c) 2010 Oracle and/or its affiliates. All rights reserved.
#
# The contents of this file are subject to the terms of either the GNU
# General Public License Version 2 only ("GPL") or the Common Development
# and Distribution License("CDDL") (collectively, the "License").  You
# may not use this file except in compliance with the License.  You can
# obtain a copy of the License at
# https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
# or packager/legal/LICENSE.txt.  See the License for the specific
# language governing permissions and limitations under the License.
#
# When distributing the software, include this License Header Notice in each
# file and include the License file at packager/legal/LICENSE.txt.
#
# GPL Classpath Exception:
# Oracle designates this particular file as subject to the "Classpath"
# exception as provided by Oracle in the GPL Version 2 section of the License
# file that accompanied this code.
#
# Modifications:
# If applicable, add the following below the License Header, with the fields
# enclosed by brackets [] replaced by your own identifying information:
# "Portions Copyright [year] [name of copyright owner]"
#
# Contributor(s):
# If you wish your version of this file to be governed by only the CDDL or
# only the GPL Version 2, indicate your decision by adding "[Contributor]
# elects to include this software in this distribution under the [CDDL or GPL
# Version 2] license."  If you don't indicate a single choice of license, a
# recipient has the option to distribute your version of this file under
# either the CDDL, the GPL Version 2 or to extend the choice of license to
# its licensees as provided above.  However, if you add GPL Version 2 code
# and therefore, elected the GPL Version 2 license, then the option applies
# only if the new code is made subject to such option by the copyright
# holder.
#

Run the sample:

1. Copy the directory /wsit/wsit/samples/ws-trust/certs/xws-security to 
<GLASSFISH_HOME> or <TOMCAT_HOME>.

2. Edit /wsit/wsit/samples/ws-trust/runtime/src/fs/build.properties to set java.home and
   tomcat.home/glassfish.home.

3. Edit /wsit/wsit/samples/ws-trust/runtime/src/common/KeyStoreCallbackHandler.java,
line 50, to set the keyStoreURL to your actual sts key store location.

Similiarly, Edit /wsit/wsit/samples/ws-trust/runtime/src/common/TrustStoreCallbackHandler.java,
line 38, to set the keyStoreURL to your actual sts trust store location.

4. Edit /wsit/wsit/samples/ws-trust/runtime/common/KeyStoreCallbackHandler.java,
line 50, to set the keyStoreURL to your actual sts key store location.

Similiarly, Edit /wsit/wsit/samples/ws-trust/runtime/src/fs/etc/service/PingService.wsdl,
line 133-134 to replace $WSIT_HOME with your Glassfish/Tomcat location.

5. Start tomcat or glassfish.

6. To run the sample, go to
   /wsit/wsit/samples/ws-trust/validate/src/fs, and run "ant run-sample".

7. You will be prompted to enter the username/password. Two pairs alice/alice, bob/bob
   are pre-configured to use with this sample.
