/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tga.search.domain.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @version:
 * @date:
 */
public class DatabaseException extends AbstractServiceException {

    /**
     * Database Exception - Bad Request
     * @param _msg
     */
    public DatabaseException(String _msg) {
        super(_msg);
    }

    /**
     * Database Exception - Bad Request
     * @param _e
     */
    public DatabaseException(Throwable _e) {
        super(_e);
    }

    /**
     * Database Exception - Bad Request
     * @param _msg
     * @param _e
     */
    public DatabaseException(String _msg, Throwable _e) {
        super(_msg, _e);
    }

    /**
     * Database Exception
     * @param _msg
     * @param badRequest
     * @param _e
     */
    public DatabaseException(String _msg, HttpStatus badRequest, Throwable _e) {
        super(_msg, badRequest, _e);
    }
}
