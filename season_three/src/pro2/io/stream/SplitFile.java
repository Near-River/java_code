package pro2.io.stream;

import pro2.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 文件分割类
 * Created by Near on 2015/12/3.
 */
public class SplitFile {
    // 文件路径
    private String path;
    // 分割块数
    private int size;
    // 每块的分配大小
    private long blockSize;
    // 分割块的名称集合
    private List<String> blockPath;

    public SplitFile(String path) {
        this(path, 1024);
    }

    public SplitFile(String path, long blockSize) {
        blockPath = new ArrayList<String>();
        this.path = path;
        this.blockSize = blockSize;

        init();
    }

    private void init() {
        File src = null;
        if (path == null || !((src = new File(path)).exists())) {
            return;
        }
        if (src.isDirectory()) {
            return;
        }
        // 计算分割的块数和每块的分配大小
        long length = src.length();
        blockSize = (blockSize > length) ? length : blockSize;
        size = (int) Math.ceil(length * 1.0 / blockSize);
    }

    // 初始化分割文件的路径名称集合
    private void initBlocksName(String destPath) {
        String fileName = new File(this.path).getName();
        // 如果目标文件夹不存在，则自动生成
        File dest = new File(destPath);
        dest.mkdirs();
        for (int i = 0; i < size; i++) {
            this.blockPath.add(dest.getAbsolutePath() + "/" + fileName + "_part" + (i + 1));
        }
    }

    /**
     * 根据目标路径来生成分割文件
     * @param destPath
     */
    public void split(String destPath) {
        long beginPos = 0L;
        long actualBlockSize = this.blockSize;
        initBlocksName(destPath);

        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                actualBlockSize = new File(path).length() - beginPos;
            }
            splitDetail(i, beginPos, actualBlockSize);
            beginPos += blockSize;
        }
    }

    /**
     * 为每个分割块生成对应的分割文件
     * @param blockIndex
     * @param beginPos
     * @param actualBlockSize
     */
    private void splitDetail(int blockIndex, long beginPos, long actualBlockSize) {
        File src = new File(this.path);
        File dest = new File(this.blockPath.get(blockIndex));

        RandomAccessFile randomAccessFile = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            randomAccessFile = new RandomAccessFile(src, "r");
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] bytes = new byte[1024];
            int len = 0;
            randomAccessFile.seek(beginPos);
            while ((len = randomAccessFile.read(bytes)) != -1) {
                if (actualBlockSize < len) {
                    bufferedOutputStream.write(bytes, 0, (int) actualBlockSize);
                    break;
                } else {
                    bufferedOutputStream.write(bytes, 0, len);
                }
                actualBlockSize -= len;
            }
            bufferedOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(bufferedOutputStream, randomAccessFile);
        }
    }

    /**
     * 做文件的合并操作
     * @param srcDir
     * @param destPath
     */
    public void merge(String srcDir, String destPath) {
        File dir = new File(srcDir);
        String[] filesName = dir.list();
        this.getBlockPath().clear();
        for (String s : filesName) {
            this.getBlockPath().add(dir.getAbsolutePath() + "/" + s);
        }

        File dest = new File(destPath);
        InputStream inputStream = null;
        OutputStream outputStream = null;

        for (String part : this.blockPath) {
            try {
                inputStream = new BufferedInputStream(new FileInputStream(new File(part)));
                outputStream = new BufferedOutputStream(new FileOutputStream(dest, true));
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
                outputStream.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                FileUtils.close(outputStream, inputStream);
            }
        }
    }

    public void mergePlus(String srcDir, String destPath) {
        File dir = new File(srcDir);
        String[] filesName = dir.list();
        this.getBlockPath().clear();
        for (String s : filesName) {
            this.getBlockPath().add(dir.getAbsolutePath() + "/" + s);
        }

        File dest = new File(destPath);
        OutputStream outputStream = null;
        SequenceInputStream sequenceInputStream = null;

        Vector<InputStream> vector = new Vector<InputStream>();
        for (String part : this.blockPath) {
            try {
                vector.add(new BufferedInputStream(new FileInputStream(new File(part))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            // public SequenceInputStream(Enumeration<? extends InputStream> e)
            sequenceInputStream = new SequenceInputStream(vector.elements());
            outputStream = new BufferedOutputStream(new FileOutputStream(dest, true));

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = sequenceInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(outputStream, sequenceInputStream);
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(long blockSize) {
        this.blockSize = blockSize;
    }

    public List<String> getBlockPath() {
        return blockPath;
    }

    public void setBlockPath(List<String> blockPath) {
        this.blockPath = blockPath;
    }
}
