import request from './request'

/**
 * 上传文件
 * @param {File} file - 文件对象
 * @returns {Promise} 上传结果
 */
export function uploadFile(file) {
    const formData = new FormData();
    formData.append('file', file);
    return request({
        url: '/file/upload',
        method: 'post',
        data: formData,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}

/**
 * 获取文件列表
 * @returns {Promise} 文件列表
 */
export function getFileList() {
    return request({
        url: '/file/list',
        method: 'get'
    });
}

/**
 * 删除文件
 * @param {string} filename - 文件名
 * @returns {Promise} 删除结果
 */
export function deleteFile(filename) {
    return request({
        url: `/file/${filename}`,
        method: 'delete'
    });
}

/**
 * 获取文件下载链接
 * @param {string} filename - 文件名
 * @returns {string} 下载链接
 */
export function getFileDownloadUrl(filename) {
    return `${request.defaults.baseURL}/file/download/${filename}`;
} 