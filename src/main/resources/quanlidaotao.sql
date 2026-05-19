USE quanlidaotao;
GO

-- =============================================
-- 1. Bảng academic_years (Niên khóa)
-- =============================================
CREATE TABLE academic_years (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    code NVARCHAR(50) NOT NULL UNIQUE,
    name NVARCHAR(100) NOT NULL,
    year NVARCHAR(20),
    description NVARCHAR(255),
    start_date DATE,
    end_date DATE,
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by UNIQUEIDENTIFIER,
    updated_by UNIQUEIDENTIFIER,
    deleted_at DATETIME2,
    deleted_by UNIQUEIDENTIFIER,
    is_active BIT DEFAULT 1
);

-- =============================================
-- 2. Bảng majors (Ngành đào tạo)
-- =============================================
CREATE TABLE majors (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    department_id UNIQUEIDENTIFIER,
    code NVARCHAR(100) NOT NULL UNIQUE,
    name NVARCHAR(255) NOT NULL,
    description NVARCHAR(MAX),
    effective_date DATETIME2,
    expiry_date DATETIME2,
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by UNIQUEIDENTIFIER,
    updated_by UNIQUEIDENTIFIER,
    deleted_at DATETIME2,
    deleted_by UNIQUEIDENTIFIER,
    is_active BIT DEFAULT 1
);

-- =============================================
-- 3. Bảng training_programs
-- =============================================
CREATE TABLE training_programs (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    code NVARCHAR(100) NOT NULL UNIQUE,
    name NVARCHAR(255) NOT NULL,
    name_en NVARCHAR(255),
    major_id UNIQUEIDENTIFIER,
    academic_year_id UNIQUEIDENTIFIER,
    department_id UNIQUEIDENTIFIER,
    degree_level NVARCHAR(50),
    education_type NVARCHAR(50),
    total_credits DECIMAL(5,1),
    required_credits DECIMAL(5,1),
    elective_credits DECIMAL(5,1),
    internship_credits DECIMAL(5,1),
    thesis_credits DECIMAL(5,1),
    admission_year DATE,
    duration_years DECIMAL(5,1),
    max_duration_years DECIMAL(5,1),
    effective_date DATE,
    expiry_date DATE,
    description NVARCHAR(MAX),
    objectives NVARCHAR(MAX),
    learning_outcomes NVARCHAR(MAX),
    version NVARCHAR(20),
    status NVARCHAR(50) DEFAULT N'Đang đào tạo',
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by UNIQUEIDENTIFIER,
    updated_by UNIQUEIDENTIFIER,
    deleted_at DATETIME2,
    deleted_by UNIQUEIDENTIFIER,
    is_active BIT DEFAULT 1
);

-- =============================================
-- 4. Bảng courses (Học phần)
-- =============================================
CREATE TABLE courses (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    department_id UNIQUEIDENTIFIER,
    code NVARCHAR(100) NOT NULL UNIQUE,
    name NVARCHAR(255) NOT NULL,
    name_en NVARCHAR(255),
    credits DECIMAL(5,1) NOT NULL,
    course_type NVARCHAR(50),
    theory_hours DECIMAL(5,1),
    practice_hours DECIMAL(5,1),
    self_study_hours DECIMAL(5,1),
    internship_hours DECIMAL(5,1),
    description NVARCHAR(MAX),
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by UNIQUEIDENTIFIER,
    updated_by UNIQUEIDENTIFIER,
    deleted_at DATETIME2,
    deleted_by UNIQUEIDENTIFIER,
    is_active BIT DEFAULT 1
);

-- =============================================
-- 5. Bảng training_program_courses
-- =============================================
CREATE TABLE training_program_courses (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    training_program_id UNIQUEIDENTIFIER NOT NULL,
    course_id UNIQUEIDENTIFIER NOT NULL,
    course_code NVARCHAR(100),
    course_name NVARCHAR(255),
    semester_id UNIQUEIDENTIFIER,
    semester_code NVARCHAR(100),
    academic_year NVARCHAR(20),
    is_required BIT DEFAULT 1,
    group_code NVARCHAR(50),
    credits DECIMAL(5,1),
    prerequisite_course_id UNIQUEIDENTIFIER,
    is_prerequisite_required BIT DEFAULT 0,
    note NVARCHAR(500),
    sort_order INT,
    status NVARCHAR(50) DEFAULT N'Đang đào tạo',
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by UNIQUEIDENTIFIER,
    updated_by UNIQUEIDENTIFIER,
    deleted_at DATETIME2,
    deleted_by UNIQUEIDENTIFIER,
    is_active BIT DEFAULT 1
);

-- =============================================
-- 6. Bảng course_prerequisites
-- =============================================
CREATE TABLE course_prerequisites (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    course_id UNIQUEIDENTIFIER NOT NULL,
    prerequisite_course_id UNIQUEIDENTIFIER NOT NULL,
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by UNIQUEIDENTIFIER,
    updated_by UNIQUEIDENTIFIER,
    deleted_at DATETIME2,
    deleted_by UNIQUEIDENTIFIER,
    is_active BIT DEFAULT 1
);

-- =============================================
-- TẠO INDEX
-- =============================================
CREATE INDEX idx_majors_code ON majors(code);
CREATE INDEX idx_courses_code ON courses(code);
CREATE INDEX idx_training_programs_code ON training_programs(code);
CREATE INDEX idx_tpc_program ON training_program_courses(training_program_id);
CREATE INDEX idx_tpc_course ON training_program_courses(course_id);

PRINT '✅ Đã tạo xong cấu trúc database!';


USE quanlidaotao;
GO

-- =============================================
-- 1. Academic Years (Niên khóa) - 12 records
-- =============================================
INSERT INTO academic_years (code, name, year, description, start_date, end_date, is_active) VALUES
('2023-2027', N'Niên khóa 2023-2027', '2023-2027', N'Đào tạo chính quy 4 năm', '2023-09-01', '2027-08-31', 1),
('2024-2028', N'Niên khóa 2024-2028', '2024-2028', N'Đào tạo chính quy', '2024-09-01', '2028-08-31', 1),
('2025-2029', N'Niên khóa 2025-2029', '2025-2029', N'Đào tạo chính quy', '2025-09-01', '2029-08-31', 1),
('2026-2030', N'Niên khóa 2026-2030', '2026-2030', N'Đào tạo chính quy', '2026-09-01', '2030-08-31', 1),
('2023-2025', N'Niên khóa Liên thông 2023-2025', '2023-2025', N'Liên thông Cao đẳng - Đại học', '2023-09-01', '2025-08-31', 1),
('2024-2026', N'Niên khóa Liên thông 2024-2026', '2024-2026', N'Liên thông', '2024-09-01', '2026-08-31', 1),
('2025-2027', N'Niên khóa 2025-2027', '2025-2027', N'Đào tạo ngắn hạn', '2025-09-01', '2027-08-31', 1),
('2024-2029', N'Niên khóa Thạc sĩ 2024-2029', '2024-2029', N'Đào tạo Thạc sĩ', '2024-09-01', '2029-08-31', 1),
('2025-2030', N'Niên khóa Thạc sĩ 2025-2030', '2025-2030', N'Đào tạo Thạc sĩ', '2025-09-01', '2030-08-31', 1),
('2026-2031', N'Niên khóa 2026-2031', '2026-2031', N'Đào tạo chính quy', '2026-09-01', '2031-08-31', 1),
('2023-2026', N'Niên khóa VB2 2023-2026', '2023-2026', N'Văn bằng 2', '2023-09-01', '2026-08-31', 1),
('2024-2027', N'Niên khóa VB2 2024-2027', '2024-2027', N'Văn bằng 2', '2024-09-01', '2027-08-31', 1);

-- =============================================
-- 2. Majors (Ngành đào tạo) - 12 records
-- =============================================
INSERT INTO majors (code, name, description, is_active) VALUES
('CNTT', N'Công nghệ Thông tin', N'Ngành Công nghệ Thông tin định hướng ứng dụng', 1),
('KTPM', N'Kỹ thuật Phần mềm', N'Kỹ thuật Phần mềm và Phát triển ứng dụng', 1),
('HTTT', N'Hệ thống Thông tin', N'Hệ thống Thông tin Quản lý', 1),
('AI', N'Trí tuệ Nhân tạo', N'Trí tuệ Nhân tạo và Khoa học Dữ liệu', 1),
('ANM', N'An ninh Mạng', N'An ninh mạng và An toàn thông tin', 1),
('QTKD', N'Quản trị Kinh doanh', N'Quản trị Kinh doanh', 1),
('KT', N'Kế toán', N'Kế toán Doanh nghiệp', 1),
('MKT', N'Marketing', N'Marketing và Truyền thông', 1),
('TMĐT', N'Thương mại Điện tử', N'Thương mại Điện tử', 1),
('LOG', N'Logistics', N'Logistics và Quản lý Chuỗi cung ứng', 1),
('CNPM', N'Công nghệ Phần mềm', N'Công nghệ Phần mềm', 1),
('KHMT', N'Khoa học Máy tính', N'Khoa học Máy tính', 1);

-- =============================================
-- 3. Courses (Học phần) - 12 records
-- =============================================
INSERT INTO courses (code, name, credits, course_type, description, is_active) VALUES
('IT101', N'Nhập môn Công nghệ Thông tin', 3, N'Cơ sở', N'Giới thiệu ngành CNTT', 1),
('IT102', N'Lập trình C++', 4, N'Cơ sở', N'Lập trình cơ bản', 1),
('IT201', N'Cấu trúc Dữ liệu', 4, N'Chuyên ngành', N'Cấu trúc dữ liệu và Giải thuật', 1),
('IT202', N'Lập trình Java', 4, N'Chuyên ngành', N'Lập trình hướng đối tượng', 1),
('IT203', N'Cơ sở Dữ liệu', 4, N'Chuyên ngành', N'Quản trị Cơ sở dữ liệu', 1),
('IT301', N'Mạng Máy tính', 3, N'Chuyên ngành', N'Mạng máy tính và Giao thức', 1),
('IT302', N'Trí tuệ Nhân tạo', 3, N'Chuyên ngành', N'Nhập môn AI', 1),
('IT303', N'Học Máy', 3, N'Chuyên ngành', N'Machine Learning', 1),
('IT401', N'An ninh Mạng', 3, N'Chuyên ngành', N'Bảo mật hệ thống', 1),
('BA101', N'Quản trị Kinh doanh', 3, N'Cơ sở', N'Quản trị kinh doanh cơ bản', 1),
('BA201', N'Marketing', 3, N'Chuyên ngành', N'Marketing', 1),
('AC101', N'Kế toán Cơ bản', 3, N'Cơ sở', N'Kế toán tài chính', 1);

-- =============================================
-- 4. Training Programs - 12 records
-- =============================================
INSERT INTO training_programs (code, name, major_id, total_credits, required_credits, degree_level, education_type, status, is_active)
SELECT 
    'CT2025-' + m.code,
    N'Chương trình ' + m.name + N' 2025',
    m.id,
    148.0,
    125.0,
    N'Đại học',
    N'Chính quy',
    N'Đang đào tạo',
    1
FROM majors m;

-- =============================================
-- 5. Training Program Courses (Liên kết) - 12 records
-- =============================================
INSERT INTO training_program_courses (training_program_id, course_id, course_code, course_name, 
                                      semester_code, is_required, sort_order, credits, status)
SELECT TOP 12
    tp.id,
    c.id,
    c.code,
    c.name,
    'HK1_2025',
    1,
    ROW_NUMBER() OVER (ORDER BY tp.id, c.code),
    c.credits,
    N'Đang đào tạo'
FROM training_programs tp
CROSS JOIN courses c
ORDER BY tp.id, c.code;

PRINT '✅ Đã thêm 12 dữ liệu mẫu cho tất cả các bảng thành công!';